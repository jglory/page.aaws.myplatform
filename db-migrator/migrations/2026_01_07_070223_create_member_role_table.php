<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('member_role', function (Blueprint $table) {
            $table->unsignedBigInteger('member_id')->comment('회원 고유키');
            $table->string('role', 20)->comment('역할. ADMIN, CUSTOMER, PARTNER');

            $table->dateTime('created_at')->index()->comment('생성일시');

            $table->unique(['member_id', 'role']);
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('member_role');
    }
};
